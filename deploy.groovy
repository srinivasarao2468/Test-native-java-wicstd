'''
node{
  stage('checkout'){
    checkout(scm)
    }
  stage('getting_url'){
    sh 'git config remote.origin.url'
  }
}
'''
node{
    stage('sample'){
        git 'https://github.com/srinivasarao2468/jenkins_check.git'
        properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '7', numToKeepStr: '7'))])
        purl = bat'git config remote.origin.url'
        echo purl
        bat(script: 'git config remote.origin.url > sample.txt')
        def myFile = readFile 'sample.txt'

        def gitURL = myFile.split("\n")[0]
        def elements =  gitURL.split("/")
        def projectName = elements[elements.size()-1].split("\\.")[0]
        echo projectName
    }
}
