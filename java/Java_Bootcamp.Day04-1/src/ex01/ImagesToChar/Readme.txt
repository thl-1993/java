инструкции по компиляции и запуску вашего исходного кода из консоли (не IDE).
Инструкция написана для состояния, когда консоль открыта в корневой папке проекта.

Необходимо находится в папке src/ex00/ImagesToChar (корневая папка текущего проекта)
$ cd src/ex01/ImagesToChar/

Компиляция всей папки:
$ javac -sourcepath ./src/java -classpath ./target -d ./target src/java/edu/school21/printer/app/Main.java

копируем
 cp -r ./src/resources/ ./target/resources/

Сборка проекта в jar архив:
$ jar cvf images-to-chars-printer.jar -C target .
c манифестом
$ jar -cmf ./src/manifest.txt  ./target/images-to-chars-printer.jar -C target .

Windows: (& 'C:\Program Files\Java\jdk-17.0.4\bin\jar.exe') вместо jar
 & 'C:\Program Files\Java\jdk-17.0.4\bin\jar.exe' -cmf ./src/manifest.txt  ./target/images-to-chars-printer.jar -C target .

запуск
$ java -jar ./target/images-to-chars-printer.jar
$ java -jar ./target/images-to-chars-printer.jar ./target/resources/it.bmp