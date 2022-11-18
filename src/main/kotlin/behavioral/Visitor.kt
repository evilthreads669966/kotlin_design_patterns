package behavioral

interface Story{
    val title: String
    val body: String
    fun accept(visitor: ShareStoryVisitor)
}

interface ShareStoryVisitor{
    fun visit(story: SimpleStory)
}

class SimpleStory(override val title: String, override val body: String) : Story {
    override fun accept(visitor: ShareStoryVisitor) {
        visitor.visit(this)
    }
}

class EmailVisitor: ShareStoryVisitor {
    override fun visit(story: SimpleStory) {
        println("emailing story\n${story.title}\n${story.body}")
    }
}

class SmsVisitor: ShareStoryVisitor {
    override fun visit(story: SimpleStory) {
        println("texting story\n${story.title}\n${story.body}")
    }
}

class FacebookVisitor: ShareStoryVisitor{
    override fun visit(story: SimpleStory) {
        println("posting to facebook story\n${story.title}\n${story.body}")
    }
}

class TwitterVisitor: ShareStoryVisitor{
    override fun visit(story: SimpleStory) {
        println("tweeting story\n${story.title}\n${story.body}")
    }
}

fun main(){
    val twitterVisitor = TwitterVisitor()
    val story = SimpleStory("cowboys win the superbowl", "lorem lipsum")
    story.accept(twitterVisitor)
}