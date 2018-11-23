# AnyWork项目接口文档

[TOC]

## 1. 更新记录

| 版本号 | 更新内容                                                     | 更新日期   | 更新人 |
| ------ | ------------------------------------------------------------ | ---------- | ------ |
| V1.0   | 1. 组织模块 <br>2. 章节模块<br>3. 用户模块<br>4. 意见反馈<br>5. WebSocket模块<br>6. 公告模块 | 2018/08/17 | 郑俊铭 |
| V1.1   | 增加做题模块（5.*）                                          | 2018/08/21 | 郑俊铭 |
| V1.2   | 修改错题为收藏题目（5.7,5.8），<br>做过的题目才能收藏<br>取消收藏（5.9） | 2018/09/04 | 郑俊铭 |
| V1.3   | 增加排行榜接口（6.*）<br>主要是刚进去的总排行榜和每个测试的排行榜 | 2018/09/12 | 郑俊铭 |
| V1.3.1 | 修改12.4，status增加2状态，代表获取全部公告<br>同时返回参数加了一个status状态 | 2018/09/26 | 郑俊铭 |
| V1.3.2 | 修改5.3  5.6的接口，增加了标识是否已经收藏的字段             | 2018/09/27 | 郑俊铭 |
| V1.3.3 | 意见反馈添加图片（9.*）                                      | 2018/10/31 | 郑俊铭 |



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

### 3.2 修改组织信息（老师）

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

### 3.3 删除组织（老师）

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

### 3.4 查看我创建的组织（老师）

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

### 3.5 查看我创建的某个组织下的全部学生（老师）

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

### 3.6 搜索组织（学生）

-   请求URL：http://ip:80/organization/search
-   请求方式：POST
-   数据传输格式：application/json
-   备注：如果没输入应限制不给提交
-   请求参数：

```json
{
    "organizationName": "组织名称"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [ //组织的数组
        {
            "organizationId": "组织id",
            "teacherName": "教师名字",
            "organizationName": "组织名",
            "description": "描述",
            "isJoin": "判断字段，0表示用户不属于该组，1表示用户属于该组",
            "imagePath": "组织照片路径",
        },
        {
            "organizationId": "组织id",
            "teacherName": "教师名字",
            "organizationName": "组织名",
            "description": "描述",
            "isJoin": "判断字段，0表示用户不属于该组，1表示用户属于该组",
            "imagePath": "组织照片路径",
        },
        {
            "organizationId": "组织id",
            "teacherName": "教师名字",
            "organizationName": "组织名",
            "description": "描述",
            "isJoin": "判断字段，0表示用户不属于该组，1表示用户属于该组",
            "imagePath": "组织照片路径",
        }
    ]
}
```

### 3.6 加入组织（学生）

-   请求URL：http://ip:port/organization/join
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "organizationId": "组织id",
    "token": "口令"
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

### 3.7 获取我的组织列表（学生）

-   请求URL：http://ip:port/organization/me
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：无
-   返回参数：

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [ //组织的数组
        {
            "organizationId": "组织id",
            "teacherName": "教师名字",
            "organizationName": "组织名",
            "description": "描述",
            "isJoin": "1",
            "imagePath": "组织照片路径",
        },
        {
            "organizationId": "组织id",
            "teacherName": "教师名字",
            "organizationName": "组织名",
            "description": "描述",
            "isJoin": "1",
            "imagePath": "组织照片路径",
        },
        {
            "organizationId": "组织id",
            "teacherName": "教师名字",
            "organizationName": "组织名",
            "description": "描述",
            "isJoin": "1",
            "imagePath": "组织照片路径",
        }
    ]
}
```

### 3.8 退出组织（学生）

-   请求URL：http://ip:port/organization/leave
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "organizationId": "组织ID"
}
```

-   返回参数：

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": null
}
```

## 4. 章节模块（老师）

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

## 5. 做题模块

### 5.1 获取组织下的章节列表

-   请求URL：http://ip:port/test/chapter
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "organizationId": "组织ID" // int
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [
        {
            "chapterId": "章节id", //int
            "organizationId": "组织id" , //int
            "chapterName": "章节名称" //string
        },
        {
            "chapterId": "章节id", //int
            "organizationId": "组织id" , //int
            "chapterName": "章节名称" //string
        }
    ]
}
```

### 5.2 获取试卷概要列表

-   请求URL：http://ip:port/test/list
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "organizationId": "组织ID",  // int
    "chapter": "章节ID", // int，如果是请求考试，这个字段为0
    "testPaperType": "要哪种类型的试卷" // int，1是考试，2是预习题，3是课后复习题
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [
        {
            "testpaperId": "试卷id", // int
            "testpaperTitle": "试卷标题",
            "status": "用于标志当前登录用户与这份试卷的关系", // 1为已完成，2是完成了一部分，3是还未做
            "createTime": "开始时间",
            "endingTime": "结束时间",
            "timeStatus": "时间状态", // 1为该试卷已经过了时间，无法继续做题，2是正在做题时间内，3是还没到做题时间 int
            "totalQuestions": "题目总数", // int 与下面一个属性只有在status状态为2的时候才有数值，其余情况都为0
            "doneQuestions": "已做题目数量", // int
            "testpaperScore": "试卷总分", // double
            "score": "得分" // double，即在已完成的时候该字段有效，为该试卷的得分，其他情况为-1
        },
        {
            "testpaperId": "试卷id",
            "testpaperTitle": "试卷标题",
            "status": "用于标志当前登录用户与这份试卷的关系", // 1为已完成，2是完成了一部分，3是还未做
            "createTime": "开始时间",
            "endingTime": "结束时间",
            "timeStatus": "时间状态", // 1为该试卷已经过了时间，无法继续做题，2是正在做题时间内，3是还没到做题时间
            "totalQuestions": "题目总数", // 与下面一个属性只有在status状态为2的时候才有数值，其余情况都为0
            "doneQuestions": "已做题目数量",
            "testpaperScore": "试卷总分", // double
            "score": "得分" // double，即在已完成的时候该字段有效，为该试卷的得分，其他情况为-1
        }
	]
}
```

### 5.3 获取已完成的试卷的结果

-   请求URL：http://ip:port/test/done/detail
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID" // int 
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": {
        "studentId": "学生id", // int
        "testpaperId": "试卷id",
        "socre": "总分数，为double类型",
        "studentAnswerAnalysis":[ //数组，每道题的情况
            {
                "question": {
                    "questionId": "问题id", // int
                    "type": "题目类型" , // 1‐选择题 2‐判断题 3‐填空题 4‐问答题
                    "A": "如果为选择题 则A的内容",
                    "B": "如果为选择题 则B的内容",
                    "C": "如果为选择题 则C的内容",
                    "D": "如果为选择题 则D的内容",
                    "content": "题目内容",
                    "socre": "该题分数",
                    "other": "如果为填空题，则为填空题的空数",
                    "analysis": "解析"
                },
                "studentAnswer":"学生的答案",
                "isTrue": "是否正确 1正确 0错误", // int
                "socre": "该题得分"， // double
        		"collectionStatus": "该题是否收藏" // int 1为已收藏, 0为未收藏
            },
            {
                "question": {
                    "questionId": "问题id",
                    "type": "题目类型" , // 1‐选择题 2‐判断题 3‐填空题 4‐问答题
                    "A": "如果为选择题 则A的内容",
                    "B": "如果为选择题 则B的内容",
                    "C": "如果为选择题 则C的内容",
                    "D": "如果为选择题 则D的内容",
                    "content": "题目内容",
                    "socre": "该题分数",
                    "other": "如果为填空题，则为填空题的空数",
                    "analysis": "解析"
                },
                "studentAnswer":"学生的答案",
                "isTrue": "是否正确 1正确 0错误",
                "socre": "该题得分",
        		"collectionStatus": "该题是否收藏" // int 1为已收藏, 0为未收藏
            }
        ]
    }
}
```

### 5.4 获取详细的试题（完成一部分和还未做）

-   请求URL：http://ip:port/test/none/detail
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID", // int
    "choice": "对做一半的试卷的选择" // int 1代表继续做，2代表重新做，如果是对于还未做的试卷，值为0
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": [ //试题数组
        {
            "questionId": "问题id",
            "type": "题目类型" , // 1‐选择题 2‐判断题 3‐填空题 4‐问答题
            "A": "如果为选择题 则A的内容",
            "B": "如果为选择题 则B的内容",
            "C": "如果为选择题 则C的内容",
            "D": "如果为选择题 则D的内容",
            "content": "题目内容",
            "socre": "该题分数", // double
            "other": "如果为填空题，则为填空题的空数", // int
            "key": "学生的答案"
        },
        {
            "questionId": "问题id",
            "type": "题目类型" , // 1‐选择题 2‐判断题 3‐填空题 4‐问答题
            "A": "如果为选择题 则A的内容",
            "B": "如果为选择题 则B的内容",
            "C": "如果为选择题 则C的内容",
            "D": "如果为选择题 则D的内容",
            "content": "题目内容",
            "socre": "该题分数",
            "other": "如果为填空题，则为填空题的空数",
            "key": "学生的答案"
        }
    ]
}
```

### 5.5提交答卷获取结果

-   请求URL：http://ip:port/test/submit
-   请求方式：POST
-   数据传输格式：application/json
-   备注：填空题以#为分隔符将空与空拼起来
-   请求参数：

```json
{
    "studentId": "答题者id",
    "testpaperId": "试卷id",
    "temporarySave": "是否临时保存", // int 1为临时保存，0为正常提交,
    "endTime": "提交的时间", // string 格式为yyyy-MM-dd HH:mm:ss
    "studentAnswer": [ //学生答题的数组
        {
            "questionId": "试题id",
            "studentAnswer": "学生写的答案"
        },
        {
            "questionId": "试题id",
            "studentAnswer": "学生写的答案"
        },
        {
            "questionId": "试题id",
            "studentAnswer": "学生写的答案"
        }
    ]
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

### 5.6 具体查看学生某道题的答题情况

-   请求URL：http://ip:port/test/detail
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "questionId": "问题ID" // int
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "具体状态信息",
    "data": {
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
            "analysis": "解析
        },
        "studentAnswer":"学生的答案",
        "isTrue": "是否正确 1正确 0错误",
        "socre": "该题得分",
        "collectionStatus": "该题是否收藏" // int 1为已收藏, 0为未收藏
	}
}
```

### 5.7 收藏题目

-   URL：http://ip:port/quest/collect
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数

```json
{
    "questionId": "问题ID" // int
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

### 5.8 查看收藏题目的列表

-   URL: http://ip:port/quest/collect/list
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：无
-   返回参数：

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": [
    	{
        	"questionId": "问题ID",
            "content": "题目内容"
        },
        {
        	"questionId": "问题ID",
            "content": "题目内容"
        }
    ]
}
```

### 5.9 取消收藏

-   URL：http://ip:port/quest/collect/delete
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数

```json
{
    "questionId": "问题ID" // int
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

## 6. 排行榜模块

### 6.1 查看总排行榜

-   URL：http://ip:port/leaderboard/show
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "leaderboardType": "排行榜类型" // int，1为在班级排，2是按老师教的班排
}
```

-   返回参数：

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": [
        // 前面的排名靠前
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

### 6.2 查看某套卷子的排行榜

-   URL：http://ip:port/leaderboard/paper/show
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "testpaperId": "试卷ID", // int
    "leaderboardType": "排行榜类型" // int，1为在班级排，2是按老师教的班排
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

## 7. 用户模块

### 7.1 用户注册

-   URL：http://ip:port/user/register
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "studentId": "学号",
    "email": "邮箱",
    "password": "密码",
    "phone": "手机号码",
    "mark":"0/1", //int,0表示学生，1表示教师
    "valcode": "验证码"
}
```

*   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": null
}
```

### 7.2 用户登录

-   URL：http://ip:port/user/login
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "studentId": "学号",
    "password": "密码",
    "valcode": "验证码"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
    	"userId": "用户ID",
        "userName": "用户姓名",
        "studentId": "学号",
        "phone": "手机号",
        "mark": "标志",
        "imagePath": "头像路径",
        "email": "邮箱"
    }
}
```

### 7.3 用户修改密码

-   URL：http://ip:port/user/password/change
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "oldPassword": "旧密码",
    "newPassword": "新密码"
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

### 7.4 用户更新资料

-   URL：http://ip:port/user/update
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "phone": "手机号",
    "email": "邮箱"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
    	"userId": "用户ID",
        "userName": "用户姓名",
        "studentId": "学号",
        "phone": "手机号",
        "mark": "标志",
        "imagePath": "头像路径",
        "email": "邮箱"
    }
}
```

### 7.5 用户上传头像

-   URL：http://ip:port/user/upload
-   请求方式：POST
-   数据传输格式：multipart/form-data
-   请求参数：

```json
file ： 头像文件
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": null
}
```

### 7.6 获取个人信息

-   URL：http://ip:port/user/info
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：无

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
    	"userId": "用户ID",
        "userName": "用户姓名",
        "studentId": "学号",
        "phone": "手机号",
        "mark": "标志",
        "imagePath": "头像路径",
        "email": "邮箱"
    }
}
```

### 7.7 获取特定用户信息

-   URL：http://ip:port/user/userId/info     注：userId为请求参数里面的userId值
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "userId": "用户ID"
}
```

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": {
    	"userId": "用户ID",
        "userName": "用户姓名",
        "studentId": "学号",
        "phone": "手机号",
        "mark": "标志",
        "imagePath": "头像路径",
        "email": "邮箱"
    }
}
```

### 7.8 用户退出

-   URL：http://ip:port/user/exit  
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：无

-   返回参数

```json
{
    "state": "状态码",
    "stateInfo": "状态信息",
    "data": null
}
```

### 7.9 忘记密码

-   URL：http://ip:port/user/forget
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
	"email": "邮箱"   
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

### 7.10 用户验证邮箱密钥重置密码

-   请求URL：http://ip:port/utils/reset
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
	"email":"邮箱",
	"ciphertext":"密钥" 
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

### 7.11 忘记密码之提交新密码

-   请求URL：http://ip:port/user/forget/new
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "password": "密码",
    "repeatPassword": "重复密码"
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

### 7.12 用户获取验证码

-   请求URL：http://ip:port/utils/valcode
-   请求方式：GET
-   请求参数：无
-   返回参数：图片

## 9. 意见反馈

### 9.1 添加意见反馈

-   请求URL：http://ip:port/suggest/add

-   请求方式：POST

-   数据传输格式：multipart/form-data

-   请求参数：

    | description | 意见描述 |
    | ----------- | -------- |
    | file        | 图片     |

-   返回参数：

```json
{
	"state": "状态码",
    "stateInfo": "状态信息",
	"data": null
}
```

### 9.2 查看意见反馈

-   请求URL：http://ip:port/suggest/show
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：无

-   返回参数：

```json
{
	"state": "状态码",
    "stateInfo": "状态信息",
    "data": [
        {
            "userName": "用户名",
            "description": "意见描述",
            "imagePath": "图片路径"
        },
        {
            "userName": "用户名",
            "description": "意见描述",
            "imagePath": "图片路径"
        }
    ]
}
```
## 11. WebSocket模块

WebSocket连接：ws://ip:8080/websocket/{userId} 

userId为用户的ID

### 11.1 在线人数统计

在连接上WebSocket时，即会发送在线人数信息，以后每五分钟发一次在线人数信息

返回参数

```json
{
    "type": "类型", // int类型。注明该消息为在线人数，1为在线人数，2是公告推送
    "onlineCount": "人数", // int类型，在线人数
}
```

### 11.2 公告推送

```json
{
    "messageId": "公告ID",
    "type": "类型", // int类型。注明该消息为公告推送，1为在线人数，2是公告推送
    "title": "标题",
    "content": "内容",
    "publisher": "发布人",
    "status": "状态" // 0为未读，1为已读
}
```

## 12 公告模块

### 12.1 发布公告（老师）

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

### 12.2 查看自己发过的公告列表（老师）

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

### 12.3 删除公告（老师）

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

### 12.4 查看公告（学生）

-   请求URL：http://ip:port/message/show
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "status": "状态",  // int，1代表要获取已读公告，0代表要获取未读公告，2代表获取全部公告
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
                "publisher": "发布人",
                "status": "公告状态" // int 1已读，0未读
            },
            {
                "messageId": "公告ID", // int
                "title": "标题",
                "content": "内容",
                "createTime": "创建时间",
                "publisher": "发布人",
                "status": "公告状态" // int 1已读，0未读
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

### 12.5 标志公告为已读

-   请求URL：http://ip:port/message/change
-   请求方式：POST
-   数据传输格式：application/json
-   请求参数：

```json
{
    "messageId": "公告ID"
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

