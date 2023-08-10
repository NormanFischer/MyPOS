vite build ./mypos-server-dashboard
echo "Done building"
if [ -d "./MyPosServer/src/main/resources/static" ];
then
    echo "Replacing static folder in Spring boot resources folder"
    rm -rf ./MyPosServer/src/main/resources/static
fi
mv -f ./mypos-server-dashboard/static ./MyPosServer/src/main/resources
