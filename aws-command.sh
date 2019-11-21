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
screen -X -S engima quit
echo 'Creating .env'
cp ENV.SAMPLE .env
echo 'Create executable...'
sudo docker-compose up --build
echo 'Entering screen...'
screen -d -m -S engima ./start-server.sh