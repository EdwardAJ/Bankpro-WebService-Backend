echo 'Fetch user...'
whoami
echo 'Curdir..'
ls
cd /home/ubuntu/ws-bank
git stash
git pull origin feature-virtualaccount
echo 'Removing existing container...'
sudo docker stop wsbankcontainer && sleep 10
echo 'Building images...'
sudo docker build -t wsbank .
echo 'Run the container...'
sudo docker run -d --rm --name wsbankcontainer -p 8081:8081 wsbank
echo 'Process done.'