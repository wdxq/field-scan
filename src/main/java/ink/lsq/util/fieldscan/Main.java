package ink.lsq.util.fieldscan;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            Map<Class<?>, Map<String, String>> result = new HashMap<>();
            doScan(Test.class, result, null);
            for (Map.Entry<Class<?>, Map<String, String>> classListEntry : result.entrySet()) {
                String name = classListEntry.getKey().getSimpleName();
                System.out.println(name);
                List<String> lines = new ArrayList<>();
                for (Map.Entry<String, String> field : classListEntry.getValue().entrySet()) {
                    String line = "\"" + field.getKey() + "\",\"" + field.getValue() + "\"";
                    System.out.println(line);
                    lines.add(line);
                }
                FileUtils.writeLines(new File("/Users/lsq/Desktop/scan/" + name + ".csv"), lines);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void doScan(Class<?> baseClass, Map<Class<?>, Map<String, String>> result, Class<?> topClass) {
        Class<?> resultClass = topClass == null ? baseClass : topClass;
        if (!Object.class.equals(baseClass.getSuperclass())) {
            doScan(baseClass.getSuperclass(), result, resultClass);
        }
        Map<String, String> current = result.computeIfAbsent(resultClass, k -> new HashMap<>());
        for (Field declaredField : baseClass.getDeclaredFields()) {
            String simpleName = declaredField.getType().getSimpleName();
            String typeName = declaredField.getGenericType().getTypeName();
            current.put(declaredField.getName(), typeName.substring(typeName.indexOf(simpleName)));
        }
    }

}
