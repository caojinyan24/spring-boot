在研发测试环境, 会有多套环境, 每套环境部署一套服务, 不同环境的服务需要使用相同的数据库. 为了减少测试环境数据库切换成本, 希望能够以一个比较简单的配置的方式, 实现测试环境数据库的动态切换
# 实现方案
实现方式，通过对外提供公共方法接入，降低改造成本,业务方通过调用公共方法接入
数据库相关配置通过Apollo配置路由
环境的配置获取逻辑
Apollo新增两个配置
配置一:
测试环境和数据库配置的映射关系，示例：{"env001":{"servicename":["dbname_env1"]}},"envdefault":{}}。 其中key为env环境名称，value为这个环境下所有服务使用的数据库：value为map格式，key为本服务名称（通过环境变量DIDIENV_ODIN_SERVICE_NAME，解析句点前一部分），value为数据库名称，数据库名称可以使用数据库名称+env环境来拼，方便接口获取。
如果env001用到了tradecenter数据库，但没有找到对应的配置，则找default环境的配置.
配置二:
数据库配置{"dbname_env1":{"userName":"","password":""}}。其中key为数据库名称+env环境标识。这里的key对应
数据库名称为enum
说明:Apollo解析获取配置的原则：在第二个Apollo配置好各个数据库的默认配置之后，对一个新拉起的env环境，可以不添加任何其他配置run起来
配置说明(for测试同学)
两个配置
配置样式:
{
"dbname_env1": {
"user": "",
"password": "",
"host": "",
"port": ,
"dbname": "dbname"
}
}
其中
`ccscore`取sdk中定义的数据库枚举值,标识和线上使用的数据库对标的唯一标识
`creditcard_env1`指申请的env环境的数据库的名称
配置样式
{
"env315": {
"servicename": [
"dbname_env1"
]
}
}
其中
`env315` 表示env环境标识
