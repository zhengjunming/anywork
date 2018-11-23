# AnyWork项目教师端接口文档

[TOC]

## 1. 更新记录

| 版本号 | 更新内容 | 更新日期 | 更新人 |
| ------ | -------- | -------- | ------ |
| V1.0   |          |          |        |
|        |          |          |        |
|        |          |          |        |
|        |          |          |        |

## 2. 数据格式统一标准

-   后台返回统一格式（json）

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": "返回数据，若非查询类操作，则返回null"
}
```

## 3. 组织模块

### 3.1 创建组织（老师）

-   URL：http://ip:port/organization/create
-   请求方式：POST
-   数据传输格式：multipart/form-data
-   请求参数

| file             | 组织头像文件 |
| ---------------- | ------------ |
| organizationName | 组织名称     |
| description      | 组织描述     |
| token            | 组织口令     |

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data":
    {
        "organizationId": "组织id", //int
        "teacherName": "教师名字", //string
        "organizationName": "组织名", //string
        "description": "描述", //string
        "token": "口令", // string
        "imagePath": "组织头像路径"
    }
}
```

### 3.2 修改组织信息

-   请求URL：http://ip:port/organization/alter
-   请求方式：POST
-   数据传输格式：multipart/form-data
-   请求参数

| organizationId   | 组织ID       |
| ---------------- | ------------ |
| file             | 组织头像文件 |
| organizationName | 组织名称     |
| description      | 组织描述     |

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data":
    {
        "organizationId": "组织id", //int
        "teacherName": "教师名字", //string
        "organizationName": "组织名", //string
        "description": "描述", //string
        "token": "口令", // string
        "imagePath": "组织头像路径"
    }
}
```

### 3.3 删除组织

-   请求URL：http://ip:port/organization/delete
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数

```json
{
	"organizationId": "组织id", //int
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": null
}
```

### 3.4 查看我创建的组织

-   请求URL：http://ip:port/organization/myOrganization
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：无
-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [ //组织的数组
        {
            "organizationId": "组织id", //int
            "teacherName": "教师名字", //string
            "organizationName": "组织名", //string
            "description": "描述", //string
            "count":"人数", //int
            "imagePath": "组织头像路径"
        },
        {
            "organizationId": "组织id", //int
            "teacherName": "教师名字", //string
            "organizationName": "组织名", //string
            "description": "描述", //string
            "count":"人数", //int
            "imagePath": "组织头像路径"
        },
        {
            "organizationId": "组织id", //int
            "teacherName": "教师名字", //string
            "organizationName": "组织名", //string
            "description": "描述", //string
            "count":"人数", //int
            "imagePath": "组织头像路径"
        }
    ]
}	
```

### 3.5 查看我创建的某个组织下的全部学生

-   请求URL：http://ip:port/organization/student
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "organizationId": "组织ID"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [
        {
            "userId": "学生id",
            "userName": "学生姓名",
            "studentId": "学号",
            "email": "邮箱",
            "phone": "电话"
        },
        {
            "userId": "学生id",
            "userName": "学生姓名",
            "studentId": "学号",
            "email": "邮箱",
            "phone": "电话"
        },
        {
            "userId": "学生id",
            "userName": "学生姓名",
            "studentId": "学号",
            "email": "邮箱",
            "phone": "电话"
        }
    ]
}
```

## 4. 章节模块

### 4.1 添加章节

-   请求URL：http://ip:80/test/addChapter
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "organizationId":"组织id", //int
    "chapterName":"章节名称" //string
}	
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "chapterId": "章节id", // int
        "organizationId": "组织id", // int
        "chapterName": "章节名称" // string
	}
}
```

### 4.2 删除章节

-   请求URL：http://ip:80/test/deleteChapter
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "chapterId": "章节ID"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": null
}
```

### 4.3 修改章节信息

-   请求URL：http://ip:80/test/chapter/update
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "chapterId":"章节id", //int
    "chapterName":"章节名称" //string
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "chapterId": "章节id", // int
        "organizationId": "组织id", // int
        "chapterName": "章节名称" // string
	}
}
```

## 5. 试卷模块

### 5.1 试卷发布

-   备注：试卷发布后老师所带的班都能看到

-   请求URL：http://ip:80/paper/publish
-   请求方式：POST
-   数据传输格式：multipart/form-data
-   请求参数：

| 字段           | 备注                                             |
| -------------- | ------------------------------------------------ |
| testpaperTitle | 试卷标题                                         |
| testpaperType  | 试卷类型，int，1是考试，2是预习题，3是课后复习题 |
| createTime     | 开始时间　格式yyyy-MM-dd HH:mm:ss                |
| endingTime     | 结束时间                                         |
| chapterId      | 章节ID                                           |
| file           | 试卷文件                                         |

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "testpaperId": "试卷ID",
        "testpaperTitle": "试卷标题",
        "createTime": "开始时间", // 后台传过来的为date类型，以下都是
        "endingTime": "结束时间",
        "testpaperType": "试卷类型",
        "testpaperScore": "试卷总分",
        "questions": [
            {
                "questionId": 0,
                "type": 1,
                "key": "A",
                "content": "选择题内容",
                "socre": 2,
                "testpaperId": 0,
                "other": 0,
                "b": "B",
                "a": "A",
                "d": "D",
                "c": "C",
                "analysis": "解析"
            },
            {
                "questionId": 0,
                "type": 2,
                "key": "1",
                "content": "判断题内容",
                "socre": 2,
                "testpaperId": 0,
                "other": 0,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析"
            },
            {
                "questionId": 0,
                "type": 3,
                "key": "填#空",
                "content": "填空(___1___)题(___2___)内容",
                "socre": 0,
                "testpaperId": 0,
                "other": 5,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析"
            },
            {
                "questionId": 0,
                "type": 4,
                "key": "问答题答案",
                "content": "问答题内容",
                "socre": 10,
                "testpaperId": 0,
                "other": 0,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析"
            }
        ]
	}
}
```

### 5.2 修改试卷附加信息

-   请求URL：http://ip:80/paper/update
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID",
    "testpaperTitle": "试卷标题",
    "testpaperType": "试卷类型，int，1是考试，2是预习题，3是课后复习题",
    "createTime": "开始时间　格式yyyy-MM-dd HH:mm:ss",
    "endingTime": "结束时间"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": null
}
```

### 5.3 删除已发布的试卷

-   请求URL：http://ip:80/paper/delete
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": null
}
```

### 5.4 查看试卷内容（预览）

-   请求URL：http://ip:80/paper/show
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "testpaperId": "试卷ID",
        "testpaperTitle": "试卷标题",
        "createTime": "开始时间",
        "endingTime": "结束时间",
        "testpaperType": "试卷类型",
        "testpaperScore": "试卷总分",
        "questions": [
            {
                "questionId": 0,
                "type": 1,
                "key": "A",
                "content": "选择题内容",
                "socre": 2,
                "testpaperId": 0,
                "other": 0,
                "b": "B",
                "a": "A",
                "d": "D",
                "c": "C",
                "analysis": "解析"
            },
            {
                "questionId": 0,
                "type": 2,
                "key": "1",
                "content": "判断题内容",
                "socre": 2,
                "testpaperId": 0,
                "other": 0,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析"
            },
            {
                "questionId": 0,
                "type": 3,
                "key": "填#空",
                "content": "填空(___1___)题(___2___)内容",
                "socre": 0,
                "testpaperId": 0,
                "other": 5,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析"
            },
            {
                "questionId": 0,
                "type": 4,
                "key": "问答题答案",
                "content": "问答题内容",
                "socre": 10,
                "testpaperId": 0,
                "other": 0,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析"
            }
        ]
	}
}
```

### 5.5 试卷分析

-   请求URL：http://ip:80/paper/analyse
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID",
    "organizationId": "组织ID" // 分析以组织为单位，如果想看全部班的情况，此字段为0
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "testpaperId": "试卷ID",
        "testpaperTitle": "试卷标题",
        "createTime": "开始时间",
        "endingTime": "结束时间",
        "testpaperType": "试卷类型",
        "testpaperScore": "试卷总分",
        "averageScore": "平均分",
        "questions": [
            {
                "questionId": 0,
                "type": 1,
                "key": "A",
                "content": "选择题内容",
                "socre": 2,
                "testpaperId": 0,
                "other": 0,
                "b": "B",
                "a": "A",
                "d": "D",
                "c": "C",
                "analysis": "解析",
                "errorRate": "错误率" // double
            },
            {
                "questionId": 0,
                "type": 2,
                "key": "1",
                "content": "判断题内容",
                "socre": 2,
                "testpaperId": 0,
                "other": 0,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析",
                "errorRate": "错误率" // double
            },
            {
                "questionId": 0,
                "type": 3,
                "key": "填#空",
                "content": "填空(___1___)题(___2___)内容",
                "socre": 0,
                "testpaperId": 0,
                "other": 5,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析",
                "errorRate": "错误率" // double
            },
            {
                "questionId": 0,
                "type": 4,
                "key": "问答题答案",
                "content": "问答题内容",
                "socre": 10,
                "testpaperId": 0,
                "other": 0,
                "b": null,
                "a": null,
                "d": null,
                "c": null,
                "analysis": "解析",
                "errorRate": "错误率" // double
            }
        ]
	}
}
```

### 5.6 查看一个组织里面自己发布过的试卷列表

-   请求URL：http://ip:port/paper/{organizationId}/list
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数
-   返回参数

```json
{
	"state": "状态码",
    "stateInfo": "状态信息",
	"data": [
        {
            "testpaperId": "试卷ID",
            "testpaperTitle": "试卷标题",
            "createTime": "开始时间",
            "endingTime": "结束时间",
            "haveSubject": "1爲有，０爲沒有"　// int
        },
        {
            "testpaperId": "试卷ID",
            "testpaperTitle": "试卷标题",
            "createTime": "开始时间",
            "endingTime": "结束时间",
            "haveSubject": "1爲有，０爲沒有"　// int
        }
    ]
}
```

### 5.7 老师查看某套试题中的学生完成情况列表

-   请求URL：http://ip:port/paper/student/list
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷id", //int
    "organizationId":"组织id" //int，如果要查看自己创建的全部组织的情况，该字段为0
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [ 
        {
            "studentId": "学生ID", // int
            "studentNum": "學號", // String
            "studentName": "学生姓名", //string
            "organizationName": "所在组织名称",
            "ifcheck": "是否已经评卷", //int 1是 0否
            "object": "客观题分数", //double
            "subject": "主观题分数", //double
            "ifAttend": "该学生是否参与考试", //1是 0否
            "testpaper": {
                "testpaperId": "试卷id", //int
                "testpaperTitle": "试卷标题", //string
                "createTime": "开始时间", //date
                "endingTime": "结束时间", //date
                "testpaperScore": "考试总分" //double
            }
        },
        {
            "studentId": "学生ID",
            "studentNum": "學號", // String
            "studentName": "学生姓名", //string
            "organizationName": "所在组织名称",
            "ifcheck": "是否已经评卷", //int 1是 0否
            "object": "客观题分数", //double
            "subject": "主观题分数", //double
            "ifAttend": "该学生是否参与考试", //1是 0否
            "testpaper": {
            "testpaperId": "试卷id", //int
            "testpaperTitle": "试卷标题", //string
            "createTime": "开始时间", //date
            "endingTime": "结束时间", //date
            "testpaperScore": "考试总分" //double
            }
        }
    ]
}
```

### 5.8 具体查看某学生完成过的某套试题

-   请求URL：http://ip:80/paper/student/testDetail
-   请求方式：POST
-   参数提交方式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷id", //int
    "studentId": "学生id" // int
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": {
        "studentId": "学生id",
        "testpaperId": "试卷id",
        "socre": "总分数，为double类型",
        "studentAnswerAnalysis":[ //数组，每道题的情况
            {
                "question":{
                    "questionId": "问题id",
                    "type": "题目类型" , //1‐选择题 2‐判断题 3‐填空题 4‐问答题
                    "A": "如果为选择题 则A的内容",
                    "B": "如果为选择题 则B的内容",
                    "C": "如果为选择题 则C的内容",
                    "D": "如果为选择题 则D的内容",
                    "content": "题目内容",
                    "socre": "该题分数",
                    "other": "如果为填空题，则为填空题的空数",
                    "key": "标准答案",
                    "analysis": "解析"
                },
                "studentAnswer":"学生的答案",
                "isTrue": "是否正确 1正确 0错误",
                "socre": "该题得分"
            },
            {
                "question":{
                    "questionId": "问题id",
                    "type": "题目类型" , //1‐选择题 2‐判断题 3‐填空题 4‐问答题
                    "A": "如果为选择题 则A的内容",
                    "B": "如果为选择题 则B的内容",
                    "C": "如果为选择题 则C的内容",
                    "D": "如果为选择题 则D的内容",
                    "content": "题目内容",
                    "socre": "该题分数",
                    "other": "如果为填空题，则为填空题的空数",
                    "key": "标准答案",
                    "analysis": "解析"
                },
                "studentAnswer":"学生的答案",
                "isTrue": "是否正确 1正确 0错误",
                "socre": "该题得分"
            }
        ]
    }
}
```

### 5.9 获取学生简答题答案进行评卷

-   请求URL：http://ip:80/paper/student/subject
-   请求方式：POST
-   参数提交方式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷id", //int
    "studentId": "学生id" // int
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": {
        "studentId": "学生id",
        "testpaperId": "试卷id",
        "socre": "总分数，为double类型",
        "studentAnswerAnalysis":[ //数组，每道题的情况
            {
                "question": {
                    "questionId": "问题id",
                    "content": "题目内容",
                    "socre": "该题分数",
                    "key": "标准答案",
                    "analysis": "解析"
                },
                "studentAnswer":"学生的答案",
                "isTrue": "是否正确 1正确 0错误",
                "socre": "该题得分"
            },
            {
                "question":{
                    "questionId": "问题id",
                    "content": "题目内容",
                    "socre": "该题分数",
                    "key": "标准答案",
                    "analysis": "解析"
                },
                "studentAnswer":"学生的答案",
                "isTrue": "是否正确 1正确 0错误",
                "socre": "该题得分"
            }
        ]
    }
}
```

### 5.10 老师评卷

-   请求URL：http://ip:80/paper/teacher/judge
-   请求方式：POST
-   参数提交方式：application/json
-   请求参数：

```json
{
    "studentId": "答题者id", //int
    "testpaperId": "试卷id", //int
    "teacherJudge": [ //老师评分数组
        { 
            "questionId": "试题id", //int
        	"socre": "老师给的分数" //double
        },
        { 
            "questionId": "试题id",
        	"socre": "老师给的分数"
        },
        { 
            "questionId": "试题id",
        	"socre": "老师给的分数"
        }
    ]
}
```

-   返回参数：

```json
{
	"state": "状态码",
    "stateInfo": "状态信息",
	"data": null
}
```

## 6 公告模块

### 6.1 发布公告

-   请求URL：http://ip:port/message/publish
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "title": "标题",
    "content": "内容"
}
```

-   返回参数：

```json
{
	"state": "状态码",
    "stateInfo": "状态信息",
	"data": null
}
```

### 6.2 查看自己发过的公告列表

-   请求URL：http://ip:port/message/list
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    “pageNum”: "查看哪一页",
    "pageSize": "一页的数目"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "total": 4,
        "list": [
            {
                "messageId": "公告ID", // int
                "title": "标题",
                "content": "内容",
                "createTime": "创建时间",
                "publisher": "发布人"
            },
            {
                "messageId": "公告ID", // int
                "title": "标题",
                "content": "内容",
                "createTime": "创建时间",
                "publisher": "发布人"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2,
        "firstPage": 1,
        "lastPage": 2
    }
}
```

### 6.3 删除公告

-   请求URL：http://ip:port/message/delete
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "messageId": "公告ID"
}
```

-   返回参数：

```json
{
	"state": "状态码",
    "stateInfo": "状态信息",
	"data": null
}
```

## 7. 排行榜模块

### 7.1 查看自己创建的所有组织中一套试卷的排行榜

-   URL：http://ip:port/leaderboard/teacher/show
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID" // int
}
```

-   返回参数：

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
        "organizations": [ // 组织的作用是为了下一个接口显示各个组织的排行榜
            {
                "organizationId": "组织ID",
                "organizationName": "组织名称"
            },
            {
                "organizationId": "组织ID",
                "organizationName": "组织名称"
            }
    	],
        "leaderboards": [
            // 排名前的在前面
            {
                "username": "姓名",
                "organizationName": "组织名",
                "studentId": "学号",
                "score": "分数",
                "imagePath": "头像路径"
        	},
            {
                "username": "姓名",
                "organizationName": "组织名",
                "studentId": "学号",
                "score": "分数",
                "imagePath": "头像路径"
            }
        ]
	}
}
```

### 7.2 查看某个组织某套试卷的排行榜

-   URL：http://ip:port/leaderboard/teacher/show/{organizationId}
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID" // int
}
```

-   返回参数：

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": [
        {
            "username": "姓名",
            "organizationName": "组织名",
            "studentId": "学号",
            "score": "分数",
            "imagePath": "头像路径"
      	},
        {
            "username": "姓名",
            "organizationName": "组织名",
            "studentId": "学号",
            "score": "分数",
            "imagePath": "头像路径"
      	}
	]
}
```