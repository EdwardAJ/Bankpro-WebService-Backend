echo 'Fetch user...'
whoami
echo 'Curdir..'
ls
echo 'DIR?'
ls /home/ubuntu/ws-bank
cd /home/ubuntu/ws-bank
git stash
git checkout master
git pull origin master
echo 'Deleting screen...'
screen -X -S wsbank quit
echo 'Creating .env'
cp ENV.SAMPLE .env
echo 'Create executable...'
sudo docker-compose up -d --build wsbank-tomcat
sudo docker-compose stop
echo 'Entering screen...'
screen -d -m -S wsbank sudo docker-compose up