cd ~/ws-bank
git stash
git pull origin feature-virtualaccount
sudo docker rm $(sudo docker stop $(sudo docker ps -a -q --filter ancestor=wsbank --format="{{.ID}}"))
sudo docker build -t wsbank .
sudo docker run -d --rm -p 8080:8080 wsbank”