инструкции по компиляции и запуску вашего исходного кода из консоли (не IDE).
Инструкция написана для состояния, когда консоль открыта в корневой папке проекта.

Необходимо находится в папке src/ex00/ImagesToChar (корневая папка текущего проекта)
$ cd src/ex02/ImagesToChar/

копируем
$ cp -r ./src/resources/ ./target/resources/

извлечь библиотеки (чтоб не копировать, войти в папку назначения)
cd target/
$ jar  xvf ../lib/JColor-5.5.1.jar com/
$ jar  xvf ../lib/jcommander-1.82.jar com/
Windows: (& 'C:\Program Files\Java\jdk-17.0.4\bin\jar.exe') вместо jar
cd ..

Компиляция всей папки:
$ javac -sourcepath ./src/java -classpath ./target -d ./target src/java/edu/school21/printer/app/Main.java

Сборка проекта в jar архив c манифестом
$ jar -cmf ./src/manifest.txt  ./target/images-to-chars-printer.jar -C target .

запуск
$ java -jar ./target/images-to-chars-printer.jar --black=BLACK --white=WHITE
$ java -jar ./target/images-to-chars-printer.jar ./target/resources/image.bmp --black=BLACK --white=WHITE
