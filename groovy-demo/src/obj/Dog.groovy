package obj
/**
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
class Dog extends Animal {

    def owner

    Dog(name, sound, owner) {
        super(name, sound)
        this.owner = owner
    }
}
