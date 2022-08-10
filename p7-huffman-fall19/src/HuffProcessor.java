double[][] a = new double[this.seq1.length+1][this.seq2.length+1];
double add=0.0;
double score=0.0;

for(int i=1;i<this.seq1.length;i++){

	for(int j=1;j<this.seq2.length;j++){

		if(this.seq1[i]==this.seq2[j]){
			add=match;
		}

		else{
			add=mismatch;
		}

		a[i][j]=Math.max((Math.max(0,(a[i][j-1]+gap))),(Math.max((a[i-1][j]+gap),(a[i-1][j-1]+add))));

		if(a[i][j]>=score){
			score=a[i][j];
		}
	}
}