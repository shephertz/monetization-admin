package monetization.admin


class AdminJob {
    def timeout = 5000l // execute job once in 5 seconds
//    static triggers = {
//        cron name: 'deleteTempData', cronExpression: "0 15 08 1/10 * ?"
//    }

    def execute() {
        // execute task
//        println "hola"
    }
}
