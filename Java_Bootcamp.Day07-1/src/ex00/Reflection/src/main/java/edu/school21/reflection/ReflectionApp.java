package edu.school21.reflection;


import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectionApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Classes:");
        System.out.println("  - User");
        System.out.println("  - Car");
        System.out.println("---------------------");
        System.out.print("Enter class name:\n-> ");
        String className = scanner.nextLine();

        try {
            // Загрузка выбранного класса
            Class<?> clazz = Class.forName("edu.school21.classes." + className);

            // Вывод полей класса
            System.out.println("---------------------");
            System.out.println("fields:");
            for (var field : clazz.getDeclaredFields()) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
            }

            // Вывод методов класса
            System.out.println("methods:");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println("\t" + method.getReturnType().getSimpleName() + " " + method.getName());
            }

            // Создание объекта
            System.out.println("---------------------");
            System.out.println("Let’s create an object.");

            // Получаем поля и создаем объект
            Object obj = clazz.getConstructor().newInstance();
            for (var field : clazz.getDeclaredFields()) {
                System.out.print(field.getName() + ":\n-> ");
                String value = scanner.nextLine();
                field.setAccessible(true);
                if (field.getType() == String.class) {
                    field.set(obj, value);
                } else if (field.getType() == int.class) {
                    field.set(obj, Integer.parseInt(value));
                } else if (field.getType() == double.class) {
                    field.set(obj, Double.parseDouble(value));
                }
            }

            // Печать созданного объекта
            System.out.println("Object created: " + obj);

            // Изменение поля объекта
            System.out.println("---------------------");
            System.out.print("Enter name of the field for changing:\n-> ");
            String fieldName = scanner.nextLine();
            System.out.print("Enter String value:\n-> ");
            String newValue = scanner.nextLine();
            var field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            if (field.getType() == String.class) {
                field.set(obj, newValue);
            } else if (field.getType() == int.class) {
                field.set(obj, Integer.parseInt(newValue));
            }
            System.out.println("Object updated: " + obj);

            // Вызов метода
            System.out.println("---------------------");
            System.out.print("Enter name of the method for call:\n-> ");
            String methodName = scanner.nextLine();
            Method method = clazz.getDeclaredMethod(methodName, int.class); // Пример с одним параметром
            System.out.print("Enter int value:\n-> ");
            int paramValue = Integer.parseInt(scanner.nextLine());
            Object result = method.invoke(obj, paramValue);
            System.out.println("Method returned:\n" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

