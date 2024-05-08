import os
from flask_sqlalchemy import SQLAlchemy
from flask import Flask
from flask_login import LoginManager



app = Flask(__name__)
app.config['SECRET_KEY'] = '8aa36ca3ae6d74a43276250c983fc1328f910e8acf5a0ece'
basedir = os.path.abspath(os.path.dirname(__file__))

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + os.path.join(basedir, 'shop.db')

db = SQLAlchemy(app)


login_manager = LoginManager()
login_manager.init_app(app)


#from shop.models import User, Items
from shop import routes
