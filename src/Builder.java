/*
【建造者模式】

问题：
    对象的创建过程设计多个步骤，每个步骤有不同的视线方式。
    如果将创建逻辑放在一个类中，会导致该类放大臃肿、难以维护。
    此外，需要创建不同变体的对象，就需要在该类中添加更多逻辑，使代码混乱。

解决：
    【建造者模式】将一个复杂对象的构建过程与其表示进行分离。
    它将对象的构建过程封装在一个独立的“Builder”累中，有该类逐步构建对象。
    这样，可以创建不同的建造者累来构建不同的对象变体。

角色：
    1. 产品（Product)：表示正在构建的复杂对象。建造者模式的目标就是构建这个产品。
    2. 抽象建造者（Abstract Builder）：定义构建Product的步骤和方法。
    3. 具体建造者（Concrete Builder）：实现Abstract Builder，从而创建不同的Product变体。
    4. 指导者（Director）：负责控制建造过程，通过将客户端与Concrete Builder分离，确保Product的构架是按照一定顺序和规则进行的。

作用：
    1. 分离构建过程和表示：可以将复杂对象的构建过程与最终表示进行分离，使构建过程更加清晰可控。
    2. 支持不同的表示：通过使用不同的Concrete Builder，构建不同Product变体
    3. 更好的可扩展性：通过创建新的Concrete Builder，即可添加新的Product变体
    4. 隐藏产品的内部结构：客户端只需要与Abstract Builder、Director交互，无需关心内部构建细节

场景：
    适用于需要构建[复杂对象]，构建过程设计[多个步骤或变体]。
    通过将构建过程分解为可重用步骤，Builder模式提供了一种结构化方法来创建对象。
 */

// 定义Product类 -> 手机，具有多个属性：尺寸、刷新率、镜头焦距、电池容量
class Phone{
    private float size;
    private int fps;
    private int focal;
    private int battery;

    public void setSize(float size) {
        this.size = size;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public void setFocal(int focal) {
        this.focal = focal;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "size=" + size +
                ", fps=" + fps +
                ", focal=" + focal +
                ", battery=" + battery +
                '}';
    }
}

// Abstract Builder
abstract class PhoneBuilder{
    protected Phone phone = new Phone();

    public abstract void buildSize();
    public abstract void buildFps();
    public abstract void buildFocal();
    public abstract void buildBattery();

    public Phone getPhone(){
        return phone;
    }
}

// 接下来实现两个具体的Builder，Phone15pro和Phone15promax
class Phone15proBuilder extends PhoneBuilder{

    @Override
    public void buildSize() {
        phone.setSize((float) 5.8);
    }

    @Override
    public void buildFps() {
        phone.setFps(120);
    }

    @Override
    public void buildFocal() {
        phone.setFocal(3);
    }

    @Override
    public void buildBattery() {
        phone.setBattery(3200);
    }
}

class Phone15promaxBuilder extends PhoneBuilder{

    @Override
    public void buildSize() {
        phone.setSize((float) 6.7);
    }

    @Override
    public void buildFps() {
        phone.setFps(120);
    }

    @Override
    public void buildFocal() {
        phone.setFocal(5);
    }

    @Override
    public void buildBattery() {
        phone.setBattery(4500);
    }
}

// 最后创建Director，协调创建过程，并返回Concrete Builder对象
class PhoneDirector{
    private PhoneBuilder builder;

    public PhoneDirector(PhoneBuilder builder){
        this.builder = builder;
    }

    public Phone createPhone(){
        builder.buildSize();
        builder.buildFps();
        builder.buildFocal();
        builder.buildBattery();
        return builder.getPhone();
    }
}

// Run!利用建造者模式创建不同phone
public class Builder {
    public static void main(String[] args) {
        // 由对应的Concrete Builder创建15 pro
        PhoneBuilder iphone15ProBuilder = new Phone15proBuilder();  // 创建具体builder
        PhoneDirector iphone15ProDirector = new PhoneDirector(iphone15ProBuilder);  // 创建Director
        Phone iphone15Pro = iphone15ProDirector.createPhone();
        System.out.println(iphone15Pro);

        // 由对应的Concrete Builder创建15 pro max
        PhoneBuilder iphone15ProMaxBuilder = new Phone15promaxBuilder();  // 创建具体builder
        PhoneDirector iphone15ProMaxDirector = new PhoneDirector(iphone15ProMaxBuilder);  // 创建Director
        Phone iphone15ProMax = iphone15ProMaxDirector.createPhone();
        System.out.println(iphone15ProMax);
    }
}
