
cd "E:\project\pet_java_core\pet-dao"
call mvn clean
call mvn install -P product

cd "E:\project\pet_java_core\pet-service"
call mvn clean
call mvn install -P product

cd "E:\project\pet_java_core\pet-util"
call mvn clean
call mvn install -P product

cd "E:\project\pet_java_user"
call mvn clean
call mvn install -P product

cd "E:\project\pet_java_worker"
call mvn clean
call mvn install -P product


cd "E:\project\pet_java_manage"
call mvn clean
call mvn install -P product


echo 按任意键结束
pause
exit