import ch.qos.logback.core.*;
import ch.qos.logback.core.encoder.*;
import ch.qos.logback.core.read.*;
import ch.qos.logback.core.rolling.*;
import ch.qos.logback.core.status.*;
import ch.qos.logback.classic.net.*;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

// We highly recommended that you always add a status listener just
// after the last import statement and before all other statements
statusListener(OnConsoleStatusListener)

// how often this files is scanned for changes
scan("30 minutes")

def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"

def appenderList = ["ROLLING"]

// console appender to stdout
/*appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
    }
}*/

appender("ROLLING", RollingFileAppender) {
    file = "${LOG_PATH}/output.log"
    encoder(PatternLayoutEncoder) {
        Pattern = "%d %level %thread %mdc %logger - %m%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        FileNamePattern = "${LOG_ARCHIVE}/archive-%d{yyyy-MM-dd}.zip"
         maxHistory = 1
    }
}

logger("org.hibernate", INFO)
logger("org.hibernate.type", INFO)
logger("org.hibernate.SQL", INFO)
logger("org.hibernate.type.descriptor.sql.BasicBinder", INFO)
logger("org.hibernate.engine.internal.StatisticalLoggingSessionEventListener", WARN)

root(INFO, appenderList)
