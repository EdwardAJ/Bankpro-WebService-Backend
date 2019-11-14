cd ws-bank
git stash
git pull origin feature-virtualaccount
echo 'runner' | sudo docker rm $(sudo docker stop $(sudo docker ps -a -q --filter ancestor=wsbank --format="{{.ID}}"))
echo 'runner' | sudo docker build -t wsbank .
echo 'runner' | sudo docker run -d --rm -p 8080:8080 wsbank”