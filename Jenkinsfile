pipeline{
agent{
label "${params.NODE_LABEL}"
} 
 parameters { 
 string(name: 'NODE_LABEL', defaultValue: 'node-2004', description: 'This is the node where the tests will be executed')
 string(name: 'IMAGE_NAME', defaultValue: 'sbexample:latest', description: 'This is the name of the docker image on which tests will be executed')
 booleanParam(name: 'ACCEPTANCE_TESTS', defaultValue: true, description: 'Check to run the acceptance tests')
 }  
environment{
mvnHome = tool name: 'maven'
}
stages {
	    // reference to maven
	    // ** NOTE: This 'maven-3.5.2' Maven tool must be configured in the Jenkins Global Configuration.   
	    stage('Clone Repo') { 
	     steps {
	      git 'https://github.com/codeboyatwork/springboot-acceptance.git'        
	      
	     }	      
	    } 
	    stage('Deploy Acceptance Container') {
	    when {
	      	expression {params.ACCEPTANCE_TESTS == true}
	      }
	      steps {
	      	sh 'docker run --name sbexample_acceptance -d -p 2222:2222 -p 8082:8080 tanmaydeshmukh1/${params.IMAGE_NAME}'
	      }
	    }
	    stage('Wait for Acceptance Container to load') {
	    when {
	      	expression {params.ACCEPTANCE_TESTS == true}
	      }
	      steps {
	      	sleep 60
	      }
	    }
	    stage('Run Acceptance Tests') {
	      // build project via maven
	      when {
	      	expression {params.ACCEPTANCE_TESTS == true}
	      }
	      steps{
	      	sh "'${mvnHome}/bin/mvn' clean test"
	      }
	      
	    } 
	    stage('Cleanup for Acceptance Tests') {
	    when {
	      	expression {params.ACCEPTANCE_TESTS == true}
	      }
	      steps {
	      	sh "docker rm -f sbexample_acceptance"
	      }
	    }   
}
}