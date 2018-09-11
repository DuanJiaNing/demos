package obj

import groovy.transform.ToString

/**
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
@ToString(includeNames = true, includeFields = true)
class Animal {

    def name
    def sound

    def run() {
        println "$name run"
    }

    Animal(name = 'Cat', sound = 'lound') {
        this.name = name
        this.sound = sound
    }

}
