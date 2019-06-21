
cd "E:\project\util"
call mvn clean
call mvn install

cd "E:\project\core"
call mvn clean
call mvn install

cd "E:\project\saas"
call mvn clean
call mvn install -P test

echo 按任意键结束
pause
exit