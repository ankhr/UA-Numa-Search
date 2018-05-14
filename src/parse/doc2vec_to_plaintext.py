from gensim.models.doc2vec import Doc2Vec

model = Doc2Vec.load("./tinyd2v.dat")


vocab_file = open("tinywikivocab.txt","w",encoding="utf-8")
for entry in model.docvecs.doctags.keys():
    print(entry,file=vocab_file, end="")
vocab_file.close()
vector_file = open("tinywikivectors.txt","w")
for array in model.docvecs:
    print(','.join([str(num) for num in array]),file=vector_file)
vector_file.close()


