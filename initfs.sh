files=(word books.json records.json friends.json friends.json dna.json matrix.json)

for i in {0..6}; do
  # init directory in hdfs
  echo "creating BUAA/problem$i/in"
  hadoop fs -mkdir -p BUAA/problem$i/in
  # copy input
  echo "put /artifact/problems/problem$i/${files[$i]} BUAA/problem$i/input/"
  hadoop fs -put /artifact/problems/problem$i/${files[$i]} BUAA/problem$i/in/input

done
