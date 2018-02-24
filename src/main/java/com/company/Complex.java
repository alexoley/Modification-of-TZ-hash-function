package com.company;
/**
 * If would be some problems with accuracy use strictfp
 */
public class Complex {
    final int m = 16807;
    final int x;
    final int y;

    int mod(int n, int d)
    {
        int result = n % d;
        if (result < 0)
            result += d;
        return result;
    }
    public Complex(int x) {
        this.x = x;
        this.y = 0;
    }
    public Complex(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Complex conj() {
        return new Complex(x, -y);
    }
    public Complex sub(Complex b) {
        return new Complex(mod(mod(x,m) - mod(b.x,m),m), mod(mod(y,m) - mod(b.y,m),m));
    }
    public Complex add(Complex b) {
        return new Complex(mod(mod(x,m) + mod(b.x,m),m), mod(mod(y,m) + mod(b.y,m), m));
    }
    public Complex mul(Complex b) {
        return new Complex(mod(mod(x,m) * mod(b.x,m),m) - mod(mod(y,m) * mod(b.y,m),m),
                mod(mod(x,m) * mod(b.y,m),m) + mod(mod(y,m) * mod(b.x,m),m));
    }
    public Complex mul(int b) {
        return new Complex(mod(mod(x,m) * mod(b,m),m), mod(mod(y,m) * mod(b,m),m));
    }
    /*public Complex div(Complex b) {
        return this.mul(b.conj()).mul(1 / b.len2());
    }
    public double len2() {
        return x * x + y * y;
    }
    public double abs() {
        return Math.sqrt(x * x + y * y);
    }
    public Complex norm() {
        return abs() == 0 ? new Complex(0, 0) : mul(1 / abs());
    }
    public double cross(Complex b) {
        return x * b.y - y * b.x;
    }
    double cross2(Complex b) {
        return this.conj().mul(b).y;
    }
    public double dot(Complex b) {
        return x * b.x + y * b.y;
    }
    double dot2(Complex b) {
        return this.conj().mul(b).x;
    }
    public static Complex polar(double r, double theta) {
        return new Complex(r * Math.cos(theta), r * Math.sin(theta));
    }
    public static Complex exp(Complex a) {
        return polar(Math.exp(a.x), a.y);
    }
    public double arg() {
        return Math.atan2(y, x);
    }
    public Complex rot90() {
        return new Complex(-y, x);
    }
    public Complex rotate(Complex p, double angle) {
        return p.sub(this).mul(exp(new Complex(0, angle))).add(this);
    }
    public Complex rotate2(Complex p, double angle) {
        p = p.sub(this);
        double cs = Math.cos(angle);
        double sn = Math.sin(angle);
        return new Complex(p.x * cs - p.y * sn, p.x * sn + p.y * cs).add(this);
    }
    public Complex reflect(Complex p, Complex q) {
        Complex s = q.sub(p);
        return this.sub(p).div(s).conj().mul(s).add(p);
    }
    public double proj(Complex p) {
        return dot(p) / this.abs();
    }
    public static double angle(Complex a, Complex p, Complex b) {
        a = a.sub(p);
        b = b.sub(p);
        return Math.atan2(a.cross(b), a.dot(b));
    }*/
    public boolean equals(Complex c)
    {
        if(this.x==c.x && this.y==c.y){
            return true;
        }
        else{
            return false;
        }
    }
    public Complex mod(int m)
    {
        return new Complex(mod(this.x,m), mod(this.y,m));
    }
    @Override
    public String toString() {
        return "Complex [x=" + x + ", y=" + y + "]";
    }
}
