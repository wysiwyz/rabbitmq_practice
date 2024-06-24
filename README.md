![image-20240313105928648](https://i.imgur.com/aEzgkD0.png)

這個專案主要用 Java 來練習 [RabbitMQ 官方的 7 tutorials](https://www.rabbitmq.com/tutorials)

短期：瞭解且會用 Hello world, Work queues, Publish/Subscribe, Routing, Topics, RPC, Publisher Confirms

中期：能夠比較 RabbitMQ v.s. Kafka

長期：可以在 K8s 上操作 RabbitMQ



## 1. Hello World
[Hello world - Java](https://www.rabbitmq.com/tutorials/tutorial-one-java)
Note:
- 2024/6/24: 因為`Pulling it all together`要求 amqp 跟 slf4j 在相同 classpath，要調整結構所以暫時未測

## 2. Work Queues
[Work queues - Java](https://www.rabbitmq.com/tutorials/tutorial-two-java)
Note: 
- 要先安裝 RabbitMQ (localhost, port: 5672)
- Work Queue(Task Queue) 的概念是要避免很很占用資源的任務一下子執行之後，還要等他完成
- 因此幫這類任務規劃排程，晚一點再來執行它
- 將任務封裝成 message 送進 Queue
- 背景的 worker process 會執行 pop 領出任務，最終執行這個 job
- 這個概念在 web-app 應用程式裡面很有用
