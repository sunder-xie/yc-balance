tagversion=v1.0_1
git reset --hard origin/master 
git pull 
chmod a+x onekey-docker.sh 
gradle clean && gradle build -x test 
docker build -t 10.19.13.20:5000/yc-balance:${tagversion} .
docker push 10.19.13.20:5000/yc-balance:${tagversion}

docker rmi aioptapp/yc-balance:${tagversion}
docker tag 10.19.13.20:5000/yc-balance:${tagversion} aioptapp/yc-balance:${tagversion}
docker login --username=aioptapp --password=aioptapp@123 --email=wuzhen3@asiainfo.com 
docker push aioptapp/yc-balance:${tagversion}
