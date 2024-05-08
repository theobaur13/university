#Access and logic
#from urllib import request
from flask import Flask, render_template, url_for, request, redirect, flash, session
from shop import app, db
from shop.models import Items, User 
from shop.forms import RegistrationForm, LoginForm, SortItems, CheckoutForm
from flask_login import login_user

@app.route("/", methods=["GET","POST"])
def home():
    if "activecart" not in session:
        session["activecart"] = []
    form = SortItems()
    items = Items.query.all()
    if form.sort_type.data == "price_low":
        items = Items.query.order_by(Items.price.asc())
        return render_template("home.html", title="Home", items = items, form=form)
    elif form.sort_type.data == "price_high":
        items = Items.query.order_by(Items.price.desc())
        return render_template("home.html", title="Home", items = items, form=form)
    elif form.sort_type.data == "eco_high":
        items = Items.query.order_by(Items.eco.asc())
        return render_template("home.html", title="Home", items = items, form=form)
    elif form.sort_type.data == "eco_low":
        items = Items.query.order_by(Items.eco.desc())
        return render_template("home.html", title="Home", items = items, form=form)

    return render_template("home.html", title="Home", items = items, form=form)

@app.route("/login", methods=["GET","POST"])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        user = User.query.filter_by(username=form.username.data).first()
        if user:
            if user.password == form.password.data:
                login_user(user)
                flash("Logged in")
                return redirect(url_for("home"))
        flash("Incorrect")
        return redirect(url_for("error"))
    return render_template("login.html", title="Login", form=form)

@app.route("/logout")
def logout():
    flash("Logged out")
    return redirect(url_for("home"))

@app.route("/register", methods=["GET","POST"])
def register():
    form = RegistrationForm()
    if form.validate_on_submit():
        user = User(username=form.username.data, password=form.password.data)
        db.session.add(user)
        db.session.commit()
        flash("Registered")
        return redirect(url_for("home"))
    return render_template("register.html", title="Register", form=form)

@app.route("/show_cart", methods=["GET","POST"])
def show_cart():
    if request.form.get("item_id") or request.form.get("item_id_remove"):
        if "activecart" not in session:
            session["activecart"] = []

        #add
        if request.form.get("item_id"):
            item_id = request.form.get("item_id")
            session["activecart"].append(item_id) 
            session.modified = True
            cartItems = Items.query.filter(Items.id.in_(session["activecart"])).all()
            return redirect(url_for("home"))
        #remove
        if request.form.get("item_id_remove"):
            remove_item_id = request.form.get("item_id_remove")
            session["activecart"].remove(remove_item_id)    
            session.modified = True
            cartItems = Items.query.filter(Items.id.in_(session["activecart"])).all()

            total = 0   #really need to clean this up
            for x in session["activecart"]:
                item = Items.query.filter_by(id=x).first()
                total += item.price
                total = round(total,2)
            cartItems = Items.query.filter(Items.id.in_(session["activecart"])).all()

            return render_template("show_cart.html", title="Show cart", cartItems=cartItems, total=total)
    
    #find total price
    total = 0
    for x in session["activecart"]:
        item = Items.query.filter_by(id=x).first()
        total += item.price
        total = round(total,2)
    cartItems = Items.query.filter(Items.id.in_(session["activecart"])).all()

    
    return render_template("show_cart.html", title="Show cart", cartItems=cartItems, total=total)

@app.route("/error")
def error():
    return render_template("error.html", title="error")

@app.route("/details/<int:item_id>")
def details(item_id):
    item = Items.query.get_or_404(item_id)
    return render_template("details.html", title="details", item=item)

@app.route("/checkout", methods=["GET","POST"])
def checkout():
    form = CheckoutForm()
    if form.validate_on_submit():
        return redirect(url_for("success"))
    return render_template("checkout.html", title="checkout", form=form)

@app.route("/success")
def success():
    return render_template("success.html", title="success")
