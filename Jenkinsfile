node('master') {
    checkout scm
    stage('Prepare') {
        sh './gradlew clean'
    }
    stage('Checkstyle') {
        sh './gradlew checkstyleMain checkstyleTest'
    }    
    stage('Test') {
        sh './gradlew test'
    }
}
