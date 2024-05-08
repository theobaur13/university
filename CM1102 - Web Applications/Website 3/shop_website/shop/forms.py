from email import message
from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField, SelectField, IntegerField
from wtforms.validators import DataRequired, EqualTo, Length, ValidationError, NumberRange, Regexp
from shop.models import User

class RegistrationForm(FlaskForm):
    username = StringField("Username", validators=[DataRequired(), Length(min=5, max=20)])
    password = PasswordField("Password", validators=[DataRequired(), Length(min=5, max=20)])
    confirm_password = PasswordField("Confirm password", validators=[DataRequired(), EqualTo("password")])
    submit = SubmitField("Register")
    
    def validate_username(self, username):
        user = User.query.filter_by(username=username.data).first()
        if user is not None:
            raise ValidationError("Username exists")
    

class LoginForm(FlaskForm):
    username = StringField("Username", validators=[DataRequired(), Length(min=5, max=20)])
    password = PasswordField("Password", validators=[DataRequired(), Length(min=5, max=20)])
    submit = SubmitField("Login")

class SortItems(FlaskForm):
    sort_type = SelectField("Sort by", choices=[("price_high", "High price"),("price_low", "Low price"),("eco_high","High Eco"),("eco_low","Low Eco")], default="price_high", render_kw={"onchange": "this.form.submit()"})

class CheckoutForm(FlaskForm):
    name = StringField("Name", validators=[DataRequired()])
    card_no = StringField("Card number", validators=[DataRequired(),Length(min=16, max=16), Regexp(regex='^[0-9]*$')])
    submit = SubmitField("Checkout")
