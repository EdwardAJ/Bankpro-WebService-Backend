cd /home/ubuntu/ws-bank
git stash
git pull origin feature-virtualaccount
echo 'Removing existing container...'
'sudo docker rm $(sudo docker stop $(sudo docker ps -a -q --filter ancestor=wsbank --format="{{.ID}}"))'
echo 'Building images...'
sudo docker build -t wsbank .
echo 'Run the container...'
sudo docker run -d --rm -p 8080:8080 wsbank