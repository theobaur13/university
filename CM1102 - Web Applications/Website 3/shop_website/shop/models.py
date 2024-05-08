from email import generator
from shop import db, login_manager
from flask_login import UserMixin
from werkzeug.security import generate_password_hash, check_password_hash

class Items(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.Text, nullable=False)
    price = db.Column(db.Integer, nullable=False)
    description = db.Column(db.Text, nullable=False)
    details = db.Column(db.Text, nullable=False)
    eco = db.Column(db.Integer, nullable=False)
    image_file = db.Column(db.Text, nullable=False, default='default.jpg')
    
    def __repr__(self):
        return f"Items('{self.name}','{self.price}','{self.description}','{self.details}')"

class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.Text, nullable=False)
    password = db.Column(db.Text, nullable=False)
    #hashed_password = db.Column(db.Text, nullable=False)

    '''
    @property
    def password(self):
        raise AttributeError("Password not readable")

    @password.setter
    def password(self,password):
        self.password_hash=generate_password_hash(password)

    def verify_password(self,password):
        return check_password_hash(self.password_hash,password)'''

    def __repr__(self):
        return f"User('{self.username}','{self.password}')"
    #    return f"User('{self.username}','{self.hashed_password}')"
    


class CartItems(db.Model):
    id = db.Column(db.Integer, primary_key=True)

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))