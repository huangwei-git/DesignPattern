// 定义一个图形接口
interface Shape{
    void show();
}

// 实现具体的图形类
class Circle implements Shape{
    @Override
    public void show() {
        System.out.println("create a circle");
    }
}

// 实现具体的图形类
class Rectangle implements Shape{
    @Override
    public void show() {
        System.out.println("create a rectangle");
    }
}

// 创建抽象工厂类，其中定义了create方法，子类实现这个方法用于创建【具体图像对象】
abstract class ShapeFactory{
    abstract Shape create();
}

// 具体工厂类
class CircleFacotry extends ShapeFactory{
    @Override
    Shape create() {
        return new Circle();
    }
}

// 具体工厂类
class RectangleFactory extends ShapeFactory{
    @Override
    Shape create() {
        return new Rectangle();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        ShapeFactory circleFactory = new CircleFacotry();
        Shape circle = circleFactory.create();
        circle.show();

        ShapeFactory reactangleFactory = new RectangleFactory();
        Shape rectangle = reactangleFactory.create();
        rectangle.show();
    }
}
