package com.kalusyu.leetcode

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2021/1/11 8:44
 *
 **/

data class User(val id: Long, val nickName: String, val age: Int)

data class Article(val id: Long, val userId: Long, val title: String, val content: String)

data class Reply(val id: Long, val articleId: Long, val replyContent: String)

interface IArticleOperate {
    fun send(article: Article?): Boolean
    fun update(article: Article?): Boolean
    fun list()
    fun reply(reply: Reply)
}

class ArticleOperate : IArticleOperate {
    override fun send(article: Article?): Boolean {
        return false
    }

    override fun update(article: Article?): Boolean {
        return false
    }

    override fun list() {
    }

    override fun reply(reply: Reply) {
    }
}

class Context {
    private val operate by lazy {
        ArticleOperate()
    }

    fun handle() {
        val user = User(1, "Kk", 18)
        val article = Article(1, user.id, "hello", "hello world!")
        operate.send(article)

        val reply = Reply(1, article.id, "Some reply")
        operate.reply(reply)

    }
}