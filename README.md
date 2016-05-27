## Intro

Please refer to the [Serenity BDD introduction](http://thucydides.info/docs/articles/an-introduction-to-serenity-bdd-with-cucumber.html) to understand Serenity + Cucumber + BDD concepts

## Usage

To run test cases , from command line execute below command

```sh
mvn clean verify

docker debug mode VNC

docker run -d -p 4444:4444 --name selenium-hub -e GRID_BROWSER_TIMEOUT=31000 selenium/hub:2.53.0

docker run -d -P -p 5900:5900 --link selenium-hub:hub -v /dev/shm:/dev/shm selenium/node-chrome-debug:2.53.0

docker run -d -P -p 5901:5900 --link selenium-hub:hub -v /dev/shm:/dev/shm selenium/node-chrome-debug:2.53.0

mvn verify \
  -Dskip.parallel=false \
  -Dwebdriver.remote.driver=CHROME \
  -Dwebdriver.remote.url=http://hub_container_external_ip:4444/wd/hub \
  -Dwebdriver.base.url=https://www.wunderlist.com \
  -Dwebdriver.remote.os=LINUX
```
