ObservableSource 所有被观察者的的父接口

ObservableSource(被观察者) 会关联一个 ObserableOnSubscribe

ObservableOnSubScribe 依赖 Emitter(事件发射器) ObserableOnSubscribe.subcribe(emmiter)

Emitter会关联一个Observer(观察者)


所以 被观察者被创建一个 内部会有关联传入一个ObservableOnSubcribe(这个可以有用户来创建也可以由框架来创建)

被观察者执行订阅方法 订阅观察者时  被观察者内部会创建一个事件发射器

ObserableOnSubscribe.subcribe(emmiter)会执行 最后Emmiter会通知观察者执行对应的方法



Retrofit @path 接口方法注解中路径 "/test" 这个是绝对路径 具体的url为Baseurl中的http://localhost:（不管这里后面还有没有写都不需要了 ) 拼接 /test
Http://localhost:/test
@path 接口方法注解中路径 "test" 这个是相对路径  具体URL取决于baseurl中
http://localhost:/a/b/ 这是目录文件格式  ===》 http://localhost:/a/b/test
http://localhost:/a/b 这是文件格式  ===》 http://localhost:/a/test