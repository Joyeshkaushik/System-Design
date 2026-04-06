interface Character{
    String getAbilities();
}
class Mario implements Character{
    public String getAbilities(){
        return "Mario";
    }
}
abstract class CharacterDecorator implements Character{
    public Character character;
   public CharacterDecorator(Character c){
        this.character=c;
    }
}
class HeightUp extends CharacterDecorator{
    HeightUp(Character c){
        super(c);
    }
    public String getAbilities(){
        return character.getAbilities()+"With Height up";
    }
}
class GunPowerUp extends CharacterDecorator{
    GunPowerUp(Character c){
        super(c);
    }
    public String getAbilities(){
        return character.getAbilities()+"With Power Up";
    }
}
class StarPowerUp extends CharacterDecorator{
    StarPowerUp(Character c){
        super(c);
    }
    public String getAbilities(){
        return character.getAbilities()+"With Star Power Up";
    }
}
public class DecoratorPattern{
    public static void main(String[] args){
         // Create a basic Mario character.
        Character mario = new Mario();
        System.out.println("Basic Character: " + mario.getAbilities());

        // Decorate Mario with a HeightUp power-up.
        mario = new HeightUp(mario);
        System.out.println("After HeightUp: " + mario.getAbilities());

        // Decorate Mario further with a GunPowerUp.
        mario = new GunPowerUp(mario);
        System.out.println("After GunPowerUp: " + mario.getAbilities());

        // Finally, add a StarPowerUp decoration.
        mario = new StarPowerUp(mario);
        System.out.println("After StarPowerUp: " + mario.getAbilities());


    }
}