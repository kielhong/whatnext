node('master') {
    stage('Poll') {
        checkout scm
    }
    stage('Prepare') {
        sh './gradlew clean'
    }
    stage('Checkstyle') {
        sh './gradlew checkstyleMain'
    }    
    stage('Test') {
        sh './gradlew test'
    }
}
