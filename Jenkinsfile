pipeline{
agent{
label "${params.NODE_LABEL} || node-2004"
} 
 parameters { 
 string(name: 'NODE_LABEL', defaultValue: 'node-2004', description: 'This is the node where the tests will be executed')
 string(name: 'IMAGE_NAME', defaultValue: 'sbexample:latest', description: 'This is the name of the docker image on which tests will be executed')
 booleanParam(name: 'ACCEPTANCE_TESTS', defaultValue: false, description: 'Check to run the acceptance tests')
 }  
environment{
mvnHome = tool name: 'maven'
}
stages {
	    // reference to maven
	    // ** NOTE: This 'maven-3.5.2' Maven tool must be configured in the Jenkins Global Configuration.   
	    stage('Clone Repo') { 
	     steps {
	      git 'https://github.com/codeboyatwork/springboot-test.git'        
	      
	     }	      
	    } 
	    stage('Deploy E2E Container') {
	    when {
	      	expression {params.E2E_TESTS == true}
	      }
	      steps {
	      	sh "docker run --name sbexample_e2e -d -p 2222:2222 -p 8081:8080 tanmaydeshmukh1/${params.IMAGE_NAME}"
	      }
	    }
	    stage('Wait for E2E Container to load') {
	    when {
	      	expression {params.E2E_TESTS == true}
	      }
	      steps {
	      	sleep 60
	      }
	    }
	    stage('Run E2E Tests') {
	      // build project via maven
	      when {
	      	expression {params.E2E_TESTS == true}
	      }
	      steps {
	      	sh "'${mvnHome}/bin/mvn' clean test -Dtest=com.experiment.test.e2etests.**.*Test*"
	      }    
	    }
	    stage('Cleanup for E2E Tests') {
	    when {
	      	expression {params.E2E_TESTS == true}
	      }
	      steps {
	      	sh "docker rm -f sbexample_e2e"
	      }
	    } 
	    stage('Deploy Acceptance Container') {
	    when {
	      	expression {params.ACCEPTANCE_TESTS == true}
	      }
	      steps {
	      	sh "docker run --name sbexample_acceptance -d -p 2222:2222 -p 8082:8080 tanmaydeshmukh1/${params.IMAGE_NAME}"
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
	      	sh "'${mvnHome}/bin/mvn' clean test -Dtest=com.experiment.demo.acceptancetests.**.*Test*"
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