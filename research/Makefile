# http://qiita.com/7shi/items/7b24ed23fcbea08c3615

TARGET = allopr-t.txt allopr.d allop-c.txt allop.d

all: $(TARGET)

allopr-t.txt: allopr-t.pl allopr.d
	./allopr-t.pl < allopr.d > $@

allopr.d: allopr.sh allop-c.txt
	./allopr.sh

allop-c.txt: allop-c.pl allop.d
	./allop-c.pl < allop.d > $@

allop.d: allop.sh
	./allop.sh

clean:
	rm -rf tmp

distclean: clean
	rm -f $(TARGET)
