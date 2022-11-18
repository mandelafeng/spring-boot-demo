1.先启动好此项目，然后创建一个流程：

访问：http://localhost:8080/expense/add?userId=1&money=123321

返回：提交成功.流程Id为：12501



2.查询待办列表:

访问：http://localhost:8080/expense/list?userId=1

Task[id=15007, name=出差报销]



3.同意：

访问：http://localhost:8080/expense/apply?taskId=15007

返回：processed ok!



4.生成流程图：

访问：http://localhost:8080/expense/processDiagram?processId=15007

返回如下图片：



整体流程截图如下：

