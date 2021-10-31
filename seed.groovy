folder('CI-Pipelines') {
  displayName('CI-Pipelines')
  description('CI-Pipelines')
}

def component = ["cart", "catalogue", "user", "frontend", "shipping", "payment"]
def count = (component.size)
for(int i=0;i<count;i++) {
  def j = component[i]
  pipelineJob("CI-Pipelines/${j}") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
        'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'("https://saidheeraj52056@dev.azure.com/saidheeraj52056/RoboShop/_git/${j}")
              'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('**/tags/**')
            }
            'hudson.plugins.git.BranchSpec' {
              'name'('*/main')
            }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }
}

folder('Mutable') {
  displayName('Mutable')
  description('Mutable')
}

pipelineJob('Mutable/vpc') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://saidheeraj52056@dev.azure.com/saidheeraj52056/RoboShop/_git/terraform-vpc')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('vpc/Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob('Mutable/DB') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://saidheeraj52056@dev.azure.com/saidheeraj52056/RoboShop/_git/terraform-vpc')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('databases/Jenkinsfile')
      'lightweight'(true)
    }
  }
}


for(int i=0;i<count;i++) {
  def j = component[i]
  pipelineJob("Mutable/${j}") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
        'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'("https://saidheeraj52056@dev.azure.com/saidheeraj52056/RoboShop/_git/${j}")
              'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('**/tags/**')
            }
            'hudson.plugins.git.BranchSpec' {
              'name'('*/main')
            }
          }
        }
        'scriptPath'('Jenkinsfile-infra')
        'lightweight'(true)
      }
    }
  }
}


//pipelineJob('CI-Pipelines/frontend') {
//  configure { flowdefinition ->
//    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//        'userRemoteConfigs' {
//          'hudson.plugins.git.UserRemoteConfig' {
//            'url'('https://saidheeraj52056@dev.azure.com/saidheeraj52056/RoboShop/_git/frontend')
//          }
//        }
//        'branches' {
//          'hudson.plugins.git.BranchSpec' {
//            'name'('*/main')
//          }
//        }
//      }
//      'scriptPath'('Jenkinsfile')
//      'lightweight'(true)
//    }
//  }
//}