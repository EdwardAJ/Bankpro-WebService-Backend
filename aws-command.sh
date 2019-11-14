cd /home/ubuntu/ws-bank
git stash
git pull origin feature-virtualaccount
echo 'Removing existing container...'
sudo docker stop wsbankcontainer
echo 'Building images...'
sudo docker build -t wsbank .
echo 'Run the container...'
sudo docker run -d --rm --name wsbankcontainer -p 8080:8081 wsbank