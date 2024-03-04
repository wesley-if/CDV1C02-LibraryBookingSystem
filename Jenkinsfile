pipeline{
	agent any
	tools {
		maven 'Maven 3.9.6'
		jdk 'OpenJDK11'
	}
	stages{
		stage("clean"){
			steps{
				echo "Start Clean"
				bat "mvn clean"
			}
		}
		stage("test"){
			steps{
				echo "Start Test"
				bat "mvn test"
			}
		}
		stage("test2"){
			steps{
				echo "Start SonarQube Test"
				bat "mvn sonar:sonar"
			}
		}
		stage("build"){
			steps{
				echo "Start build"
				bat "mvn install -DskipTests"
			}
		}
	}
}
