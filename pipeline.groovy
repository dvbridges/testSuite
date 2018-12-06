src/windows/pipeline.groovy
#!/usr/bin/groovy
package windows;

def build() {
  bat "dir /w"
}
def test(name) {
  bat "dir"
}

// AimTheory have a recommendation and explanation about this here
return this