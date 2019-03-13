rm(list=ls())

library(dplyr)

dat = read.csv('/Users/MacProMatt/Desktop/norm_df_forR.csv')
dat = as.data.frame(dat)
dat$rank_bon = p.adjust(dat$rank, method = "bonferroni")

#The size of this list is 2? That can't be right?
new_df = filter(dat, rank_bon <= 0.05)

#This size is 175, a little better?
dat$FDR_BH_RANK = p.adjust(dat$rank, method = "BH")
new_df = filter(dat, FDR_BH_RANK <= 0.05)

dat = dat[order(dat$FDR_BH_RANK),]

#Take the first 500 vals for plotting
head_dat = head(dat, n = 500)
head_dat$index = seq(1,500,1)
rank = seq(1,500,1)
FDR = c()
for (i in rank){
  val = (i/22283)*0.05
  FDR = c(FDR, val)
}

head_dat$thresh = FDR

plot(head_dat$index, head_dat$rank, type = 'l', xlab = 'index', col = 'blue', ylab = 'p-values', main = 'P-Value and Multiple Test Correction Threshold')
lines(head_dat$index, head_dat$thresh, type = 'l', col = 'red', )
legend("topleft", legend = c('Rank Sum P Value', 'BH Threshold (ind)'), col = c('blue', 'red'), lty = c(1,1))

#extra credit problem
new_dat = read.table('/Users/MacProMatt/Desktop/algorithms-school_work/CSCI 5461/HW1/ExtraCredit_vantveer.txt', fill = T, quote = "", stringsAsFactors=FALSE, sep = '\t', header = F, row.names = 1, na.strings=c("","NA"))

pos_df = data.frame(row.names = row.names(new_dat))
neg_df = data.frame(row.names = row.names(new_dat))

for (i in 2:length(new_dat)){
  if (new_dat[1,i] == 1){
    pos_df = cbind(pos_df, new_dat[,i])
  } else {
    neg_df = cbind(neg_df, new_dat[,i])
  }
}

neg_df = t(neg_df)
pos_df = t(pos_df)

for (j in 2:24482){
  rank = wilcox.test(pos_df[,i][,i])
}