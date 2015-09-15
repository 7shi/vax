oprs-all.txt: oprs.txt allop.d
	rm -f $@
	cat oprs.txt | while read opr; do echo "# $$opr" >> $@; grep -vF " $$opr," allop.d | grep -vF " $$opr " | grep -F " $$opr" >> $@; done

oprs.txt: allop.d
	grep -v '\.word' $< | grep -v $$'\t\w*$$' | sed $$'s/^.*\t\w* //' | sort | uniq > $@

allop.d: allop.sh
	./allop.sh

clean:
	rm -rf build
	rm -f oprs.txt allop.d
