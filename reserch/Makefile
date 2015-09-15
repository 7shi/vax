oprs.txt: allop.d
	grep -v '\.word' $< | grep -v $$'\t\w*$$' | sed 's/^.* //' | sort | uniq > $@

allop.d: allop.sh
	./allop.sh

clean:
	rm -rf build
	rm -f oprs.txt allop.d
