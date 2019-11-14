# we have already setup the DEPLOYER_SERVER in our gitlab settings which is a
# comma seperated values of ip addresses.
DEPLOY_SERVERS=$DEPLOY_SERVER

# lets split this string and convert this into array
# In UNIX, we can use this commond to do this
# ${string//substring/replacement}
# our substring is "," and we replace it with nothing.
ALL_SERVERS=(${DEPLOY_SERVERS//,/ })
echo "ALL_SERVERS ${ALL_SERVERS}"

for server in "${ALL_SERVERS[@]}"
do
  echo "deploying to ${server}"
  echo '19991312' | sudo -S ssh -i tubes2wbd.pem ubuntu@${server} “cd ~/ws-bank && git stash && git pull origin feature-virtualaccount && sudo docker rm $(sudo docker stop $(sudo docker ps -a -q --filter ancestor=wsbank --format="{{.ID}}")) && sudo docker build -t wsbank . && sudo docker run -d --rm -p 8080:8080 wsbank”
done

