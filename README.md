# jrc
j remove config 一个远程配置的开源项目

配置会存放在远程，项目中基本上可以做到没有任何配置文件，
同时，配置文件也会备份存在服务器的硬盘上面，防止jrc-server宕机，导致应用程序无法启动。
在jrc-server存活的时候，会大jrc-server中取配置文件，如果jrc-server宕机，会从硬盘中获取，硬盘中的配置文件也是会实时更新的

## jrc的特点

- 同时支持yaml和property的格式
- 支持多重继承，类似java类的多继承方式，可以对父配置进行覆盖，继承级别无限制
- 如果修改配置，客户端实时更新配置信息
- 配置信息实时保存到硬盘，当远程server不可用时，不会导致应用程序无法启动，应用程序会优先读取远程，如果无法读取，则会读取最近一次硬盘的配置
- 支持不同环境(profile)，不同版本(version)，随时可以进行切换版本，以及对历史版本的保留
- 支持不同的java环境，spring boot工程可以做到零配置，甚至连启动的端口都可以配置到jrc server，普通sprng工程，甚至非spring工程都可以使用


> **相对于其它的远程配置工程，jrc的特别之处就是支持了yaml，以及多重的继承和覆盖**，这在其它的类似项目中，都没有支持到这些功能，这也是大多数远程配置的一个局限，jrc很好的解决了这个问题


文档地址

DOCUMENT

**QQ交流群**：723624368


## 项目截图
![](https://zxj19951029.github.io/img-lib/jrc/versionConfig.png)

![](https://zxj19951029.github.io/img-lib/jrc/versionDependency.png)

![](https://zxj19951029.github.io/img-lib/jrc/versionAllConfig.png)

![](https://zxj19951029.github.io/img-lib/jrc/versionList.png)

![](https://zxj19951029.github.io/img-lib/jrc/unitList.png)

![](https://zxj19951029.github.io/img-lib/jrc/addUnit.png)

![](https://zxj19951029.github.io/img-lib/jrc/index.png)
