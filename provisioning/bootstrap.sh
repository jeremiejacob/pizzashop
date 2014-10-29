#!/bin/sh -e

# Edit the following to change the name of the database user that will be created:
APP_DB_USER=vagrant
APP_DB_PASS=dbpass

# Edit the following to change the name of the database that is created
APP_DB_NAME=pizzashop

# Edit the following to change the version of PostgreSQL that is installed
PG_VERSION=8.4

print_db_usage () {
echo "Your PostgreSQL database has been setup and can be accessed on your local machine on the forwarded port (default: 15432)"
echo "  Host: localhost"
echo "  Port: 15432"
echo "  Database: $APP_DB_NAME"
echo "  Username: $APP_DB_USER"
echo "  Password: $APP_DB_PASS"
echo ""
}

PG_PROFILE="/home/vagrant/.profile"
echo "export LANGUAGE=en_US.UTF-8" > "$PG_PROFILE"
echo "export LANG=en_US.UTF-8" > "$PG_PROFILE"
echo "export LC_ALL=en_US.UTF-8" > "$PG_PROFILE"
. "$PG_PROFILE"

PG_REPO_APT_SOURCE=/etc/apt/sources.list.d/pgdg.list
if [ ! -f "$PG_REPO_APT_SOURCE" ]
then
# Add PG apt repo:
echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > "$PG_REPO_APT_SOURCE"

# Add PGDG repo key:
wget --quiet -O - http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc | apt-key add -
fi

apt-get update
apt-get -y install "postgresql-$PG_VERSION" "postgresql-contrib-$PG_VERSION"

PG_CONF="/etc/postgresql/$PG_VERSION/main/postgresql.conf"
PG_HBA="/etc/postgresql/$PG_VERSION/main/pg_hba.conf"

# Edit postgresql.conf to change listen address to '*':
sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" "$PG_CONF"

# Append to pg_hba.conf to add password auth:
echo "host    all             all             0.0.0.0/0                     md5" >> "$PG_HBA"

# Restart so that all new config is loaded:
service postgresql restart

# Create roles and databases
cat << EOF | su - postgres -c psql
CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
CREATE DATABASE "$APP_DB_NAME" WITH OWNER="$APP_DB_USER" ENCODING='UTF8' TEMPLATE=template0 LC_COLLATE='en_US.utf8' LC_CTYPE='en_US.utf8';
EOF

#Import database schema and sample data
sudo -u "$APP_DB_USER" psql "$APP_DB_NAME" < /vagrant/provisioning/database/pizzashop_schema.sql
sudo -u "$APP_DB_USER" psql "$APP_DB_NAME" < /vagrant/provisioning/database/pizzashop_sampledata.sql
print_db_usage
